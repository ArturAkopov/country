package simple.springboots.service.country.domain;

import java.util.Date;

public record Photo (
        String description,
        Date lastModifyDate,
        String content){
}
