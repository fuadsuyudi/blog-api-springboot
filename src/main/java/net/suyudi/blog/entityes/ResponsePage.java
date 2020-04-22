package net.suyudi.blog.entityes;

import org.hibernate.annotations.Any;
import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePage<Any> {

    private Integer perpage = 10;
    private Integer page = 0;
    private Integer last_page = 0;
    private Integer total = 0;
    private Object list;

    public ResponsePage(Page<Any> data) {
        this.perpage = data.getSize();
        this.page = data.getNumber() + 1;
        this.last_page = data.getTotalPages();
        this.total = Integer.parseInt(Long.toString(data.getTotalElements()));
        this.list = data.getContent();
	}

}