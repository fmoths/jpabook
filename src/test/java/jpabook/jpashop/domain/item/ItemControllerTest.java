package jpabook.jpashop.domain.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ItemControllerTest {
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    @DisplayName("/items로 접속하면 상품리스트를 보여준다.")
//    public void accessToItemListPage(){
//        //when
//        String body = this.testRestTemplate.getForObject("/items", String.class);
//        assertThat(body).contains("<title>상품 리스트</title>");
//    }
}