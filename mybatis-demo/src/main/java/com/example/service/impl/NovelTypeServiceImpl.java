package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.NovelType;
import com.example.mapper.NovelTypeMapper;
import com.example.service.NovelTypeService;
import org.springframework.stereotype.Service;

/**
 * @author liuyzh
 * @description
 * @date 2019-12-26
 */
@Service
public class NovelTypeServiceImpl extends ServiceImpl<NovelTypeMapper, NovelType> implements NovelTypeService{
}
