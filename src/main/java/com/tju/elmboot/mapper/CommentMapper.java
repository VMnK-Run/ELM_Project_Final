package com.tju.elmboot.mapper;


import com.tju.elmboot.po.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    public List<Comment> listComment(Comment comment);

    @Insert("insert into comment(userId,businessId,foodId,content,img,star) values(#{userId},#{businessId},#{foodId},#{content},#{img},#{star})")
    @Options(useGeneratedKeys = true, keyProperty = "cmId", keyColumn = "cmId")
    public int saveComment(Comment comment);

    @Select("select * from comment where cmId=#{cmId}")
    public Comment getCommentById(Integer cmId);


    public int updateComment(Comment comment);

    @Delete("delete from comment where cmId=#{cmId}")
    public int removeComment(Integer cmId);
}
