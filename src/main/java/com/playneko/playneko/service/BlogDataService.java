package com.playneko.playneko.service;

import com.playneko.playneko.mapper.BlogDataMapper;
import com.playneko.playneko.vo.BlogDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogDataService {

    @Autowired
    public BlogDataMapper mapper;

    public int selectBlogTotal() {
        return mapper.selectBlogTotal();
    }

    public List<BlogDataVo> selectBlogList(Integer pageNum) {
        return mapper.selectBlogList(pageNum);
    }

    public List<BlogDataVo> selectSearchList(String search) {
        return mapper.selectSearchList(search);
    }

    public BlogDataVo selectDetail(Integer id) {
        return mapper.selectDetail(id);
    }
}
