package com.playneko.playneko.controller;

import com.playneko.playneko.service.BlogDataService;
import com.playneko.playneko.vo.BlogDataVo;
import com.playneko.playneko.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    // 한페이지에 존재하는 게시글 수
    private static final int BLOCK_PAGE_NUM_COUNT = 10;

    @Autowired
    BlogDataService blogDataService;

    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        int dataTotal = blogDataService.selectBlogTotal();
        Map<String, Integer> paging = paging(pageNum, dataTotal);
        List<BlogDataVo> dataList = blogDataService.selectBlogList((int)paging.get("startPage"));
        model.addAttribute("list", dataList);
        model.addAttribute("page", paging);
        return "list";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute SearchVo searchVo) {
        List<BlogDataVo> dataList = blogDataService.selectSearchList(searchVo.getSearch());
        model.addAttribute("list", dataList);
        return "search";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        BlogDataVo dataDetail = blogDataService.selectDetail(id);
        model.addAttribute("detail", dataDetail);
        return "detail";
    }

    private Map<String, Integer> paging(int currentPage, int totalCount) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int startPageNum = 1;
        int lastPageNum = BLOCK_PAGE_NUM_COUNT;
        int startPage = (currentPage - 1) * BLOCK_PAGE_NUM_COUNT;
        int lastPage = (int)(Math.ceil((totalCount / (double)BLOCK_PAGE_NUM_COUNT)));

        if (currentPage > (BLOCK_PAGE_NUM_COUNT / 2)) {
            startPageNum = currentPage - ((lastPageNum / 2) - 1);
            lastPageNum += (startPageNum - 1);
        } else {
            lastPageNum += (lastPageNum / 2) - 1;
        }

        if (currentPage >= lastPage || lastPageNum > lastPage) {
            lastPageNum = lastPage;
        }

        // 현재페이지
        map.put("currentPage", currentPage);
        // 한페이지에 보여줄 행수
        map.put("startPage", startPage);
        // 마지막 페이지 번호
        map.put("lastPage", lastPage);
        // 보여줄 첫번째 페이지 번호
        map.put("startPageNum", startPageNum);
        // 보여줄 마지막 페이지 번호
        map.put("lastPageNum", lastPageNum);

        return map;
    }
}
