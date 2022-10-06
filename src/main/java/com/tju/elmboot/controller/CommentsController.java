package com.tju.elmboot.controller;


import com.tju.elmboot.po.Cart;
import com.tju.elmboot.po.Comment;
import com.tju.elmboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CommentsController")
public class CommentsController {

    @Autowired
    private CommentService commentService;//动态注入commentService

    //调用service层
    @RequestMapping("/listComment")
    public List<Comment> listComment(Comment comment) {
        return commentService.listComment(comment);
    }

    @RequestMapping("/saveComment")
    public int saveComment(Comment comment){
        return commentService.saveComment(comment);
    }

    @RequestMapping("/getCommentById")
    public Comment getCommentById(Integer cmId){
        return commentService.getCommentById(cmId);
    }

    @RequestMapping("/removeComment")
    public int removeComment(Integer cmId){
        return commentService.removeComment(cmId);
    }

    @RequestMapping("/updateComment")
    public int updateComment(Comment comment){
        return commentService.updateComment(comment);
    }
}
