package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.CommentMapper;
import com.tju.elmboot.po.Comment;
import com.tju.elmboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> listComment(Comment comment){
        return commentMapper.listComment(comment);
    }

    @Override
    public int saveComment(Comment comment){
        return commentMapper.saveComment(comment);
    }

    @Override
    public Comment getCommentById(Integer cmId){
        return commentMapper.getCommentById(cmId);
    }

    @Override
    public int removeComment(Integer cmId){
        return commentMapper.removeComment(cmId);
    }

    @Override
    public int updateComment(Comment comment){
        return commentMapper.updateComment(comment);
    }
}
