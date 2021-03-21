package com.playneko.playneko.mapper;

import com.playneko.playneko.vo.BlogDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDataMapper {
    // 데이터 총 갯수
    int selectBlogTotal();

    // 데이터 총 갯수(검색용)
    int selectSearchTotal(String search);

    // 한페이지당 데이터 리스트
    List<BlogDataVo> selectBlogList(Integer pageNum);

    // 한페이지당 데이터 리스트(검색용)
    List<BlogDataVo> selectSearchList(String search);
}
