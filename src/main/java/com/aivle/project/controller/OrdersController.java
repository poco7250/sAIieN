package com.aivle.project.controller;

import com.aivle.project.dto.OrdersDto;
import com.aivle.project.entity.*;
import com.aivle.project.enums.OrderStatus;
import com.aivle.project.repository.ContractsRepository;
import com.aivle.project.repository.ProductsRepository;
import com.aivle.project.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class OrdersController {

    private final ContractsRepository contractsRepository;
    private final OrdersService ordersService;
    private final ProductsRepository productsRepository;

    // Read page
    @GetMapping("/orders")
    public String orders(
            @RequestParam(defaultValue = "0") int page, // 현재 페이지 번호 (0부터 시작)
            @RequestParam(defaultValue = "10") int size, // 페이지 크기
            @RequestParam(defaultValue = "") String search, // 검색어
            @RequestParam(defaultValue = "orderDate") String sortColumn, // 정렬 기준
            @RequestParam(defaultValue = "asc") String sortDirection, // 정렬 방향
            Model model
    ) {
        // 서비스에서 페이지 데이터 가져오기
        Page<OrdersEntity> ordersPage = ordersService.readOrders(page, size, search, sortColumn, sortDirection);

        // 총 페이지 수 및 표시할 페이지 범위 계산
        int totalPages = ordersPage.getTotalPages();
        int displayRange = 5; // 표시할 페이지 버튼 수
        int startPage = Math.max(0, page - displayRange / 2); // 시작 페이지
        int endPage = Math.min(totalPages, startPage + displayRange); // 종료 페이지

        // 시작 페이지와 종료 페이지 범위 조정
        if (endPage - startPage < displayRange) {
            startPage = Math.max(0, endPage - displayRange);
        }

        // 페이지 번호 생성
        List<Map<String, Object>> pageNumbers = IntStream.range(startPage, endPage)
                .mapToObj(i -> {
                    Map<String, Object> pageInfo = new HashMap<>();
                    pageInfo.put("page", i); // 페이지 번호 (0부터 시작)
                    pageInfo.put("displayPage", i + 1); // 사용자에게 보여줄 페이지 번호 (1부터 시작)
                    pageInfo.put("isActive", i == page); // 현재 페이지 여부
                    return pageInfo;
                })
                .collect(Collectors.toList());

        // Model에 데이터 추가
        model.addAttribute("orders", ordersPage.getContent()); // 현재 페이지의 데이터
        model.addAttribute("currentPage", page); // 현재 페이지
        model.addAttribute("previousPage", page - 1); // 이전 페이지
        model.addAttribute("nextPage", page + 1); // 다음 페이지
        model.addAttribute("totalPages", totalPages); // 총 페이지 수
        model.addAttribute("hasPreviousPage", page > 0); // 이전 페이지 존재 여부
        model.addAttribute("hasNextPage", page < totalPages - 1); // 다음 페이지 존재 여부
        model.addAttribute("pageNumbers", pageNumbers); // 페이지 번호 목록

        // 검색 및 정렬 데이터
        model.addAttribute("search", search); // 검색어
        model.addAttribute("sortColumn", sortColumn); // 정렬 기준
        model.addAttribute("sortDirection", sortDirection); // 정렬 방향

        return "orders/orders_read"; // Mustache 템플릿 이름
    }


    // Detail page
    @GetMapping("/orders/detail/{orderId}")
    public String ordersDetail(@PathVariable Long orderId, Model model) {
        OrdersEntity orders = ordersService.searchOrder(orderId);
        // 제품 목록 조회 후 모델에 추가 (드롭다운 메뉴용)
        List<ProductsEntity> products = productsRepository.findAll();
        List<ContractsEntity> contracts = contractsRepository.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        model.addAttribute("contracts", contracts);
        return "orders/orders_detail";
    }

    // Create order page (초기값으로 페이지 생성)
    @GetMapping("/orders/detail/create")
    public String ordersCreate(Model model) {

        OrdersEntity orders = new OrdersEntity();

        // 목록 조회 후 모델에 추가 (드롭다운 메뉴용)
        List<ProductsEntity> products = productsRepository.findAll();
        List<ContractsEntity> contracts = contractsRepository.findAll();

        orders.setOrderDate(LocalDate.now());
        orders.setSalesDate(LocalDate.now());
        orders.setOrderAmount(0F);
        orders.setOrderStatus(OrderStatus.draft);

        orders.setProductId(new ProductsEntity());
        orders.setContractId(new ContractsEntity());

        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        model.addAttribute("contracts", contracts);

        return "orders/orders_detail";
    }


    // Create new order

    @PostMapping("/orders/detail/create")
    public String saveOrder(@ModelAttribute OrdersDto ordersDto) {
        // OrdersEntity 생성 및 저장
        ordersService.createOrder(ordersDto);

        return "redirect:/orders";
    }



    // Update detail page
    @PostMapping("/orders/detail/{orderId}/update")
    public String ordersUpdate(@PathVariable("orderId") Long orderId, @ModelAttribute OrdersDto ordersDto) {
        ordersService.updateOrder(orderId, ordersDto);
        return "redirect:/orders/detail/" + orderId;
    }

    // Delete detail page
    @GetMapping("/orders/detail/{orderId}/delete")
    public String ordersDeleteDetail(@PathVariable("orderId") Long orderId) {
        ordersService.deleteOrder(orderId);
        return "redirect:/orders";
    }

    // Delete orders in bulk
    @PostMapping("/orders/detail/delete")
    public ResponseEntity<Void> deleteOrders(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        System.out.println("deleteOrders Received IDs: " + ids); // 로그 추가
        ordersService.deleteOrdersByIds(ids);
        return ResponseEntity.ok().build(); // 상태 코드 200 반환
    }
}
