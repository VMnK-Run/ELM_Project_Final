package com.tju.elmboot.mapper;


import com.tju.elmboot.po.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    //在xml映射文件中具体实现sql
    public List<Comment> listComment(Comment comment);

    @Insert("insert into comment(userId,businessId,foodId,content,img,star) values(#{userId},#{businessId},#{foodId},#{content},#{img},#{star})")
    @Options(useGeneratedKeys = true, keyProperty = "cmId", keyColumn = "cmId")//insert中没有插入cmId，利用Options注解将cmId设为自增的自动插入
    public int saveComment(Comment comment);

    @Select("select * from comment where cmId=#{cmId}")
    public Comment getCommentById(Integer cmId);


    public int updateComment(Comment comment);

    @Delete("delete from comment where cmId=#{cmId}")
    public int removeComment(Integer cmId);
}
