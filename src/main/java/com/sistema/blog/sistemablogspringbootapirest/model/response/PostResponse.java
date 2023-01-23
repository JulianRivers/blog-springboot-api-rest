package com.sistema.blog.sistemablogspringbootapirest.model.response;

import java.util.List;

import com.sistema.blog.sistemablogspringbootapirest.model.dto.PostDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {    
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;
}
