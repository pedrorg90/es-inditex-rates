package com.inditex.testjava2020.esinditexrates.repository;

import static org.junit.jupiter.api.Assertions.*;
import com.inditex.testjava2020.esinditexrates.entity.Rate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DataJpaTest
public class IRateRepositoryTests {

    @Autowired
    private IRateRepository repository;

    static DateTimeFormatter dateTimeFormatter;

    @BeforeAll
    static void beforeAll(){
        dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @BeforeEach
    public void setup(){
        repository.save(new Rate(1,1,35455, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 0,35.50,"EUR"));
        repository.save(new Rate(1,2,35455,LocalDateTime.parse("2020-06-14-15.00.00", dateTimeFormatter),LocalDateTime.parse("2020-06-14-18.30.00", dateTimeFormatter), 1,25.45,"EUR"));
        repository.save(new Rate(1,3,35455,LocalDateTime.parse("2020-06-15-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-06-15-11.00.00", dateTimeFormatter), 1,30.50,"EUR"));
        repository.save(new Rate(1,4,35455,LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 1,38.95,"EUR"));
    }

    @Nested
    class isCorrectRateTests {

        @DisplayName("Inditex rate case 1")
        @Test
        public void givenApplicationDateBrandIdProductIdCase1_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnCorrectRate() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-10.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertAll(
                    () -> assertEquals(rateResponse.getStartDate(), LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getEndDate(), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getPriceList(), 1),
                    () -> assertEquals(rateResponse.getPrice(), 35.50)
            );

        }

        @DisplayName("Inditex rate case 2")
        @Test
        public void givenApplicationDateBrandIdProductIdCase2_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnCorrectRate() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-16.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertAll(
                    () -> assertEquals(rateResponse.getStartDate(), LocalDateTime.parse("2020-06-14-15.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getEndDate(), LocalDateTime.parse("2020-06-14-18.30.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getPriceList(), 2),
                    () -> assertEquals(rateResponse.getPrice(), 25.45)
            );

        }

        @DisplayName("Inditex rate case 3")
        @Test
        public void givenApplicationDateBrandIdProductIdCase3_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnCorrectRate() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-21.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertAll(
                    () -> assertEquals(rateResponse.getStartDate(), LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getEndDate(), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getPriceList(), 1),
                    () -> assertEquals(rateResponse.getPrice(), 35.50)
            );

        }

        @DisplayName("Inditex rate case 4")
        @Test
        public void givenApplicationDateBrandIdProductIdCase4_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnCorrectRate() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15-10.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertAll(
                    () -> assertEquals(rateResponse.getStartDate(), LocalDateTime.parse("2020-06-15-00.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getEndDate(), LocalDateTime.parse("2020-06-15-11.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getPriceList(), 3),
                    () -> assertEquals(rateResponse.getPrice(), 30.50)
            );

        }

        @DisplayName("Inditex rate case 5")
        @Test
        public void givenApplicationDateBrandIdProductIdCase5_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnCorrectRate() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16-21.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertAll(
                    () -> assertEquals(rateResponse.getStartDate(), LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getEndDate(), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter)),
                    () -> assertEquals(rateResponse.getPriceList(), 4),
                    () -> assertEquals(rateResponse.getPrice(), 38.95)
            );

        }
    }

    @Nested
    class isWrongInputDataTests{

        @Test
        public void givenOutOfRangeApplicationDate_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnEmpty() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2024-06-16-21.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertNull(rateResponse);

        }

        @Test
        public void givenWrongBrandId_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnEmpty() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-10.00.00", dateTimeFormatter);
            long brandId = 2;
            long productId = 35455;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertNull(rateResponse);

        }

        @Test
        public void givenWrongProductId_WhenFindByStartDateAndEndDateAndBrandIdAndProductId_ThenReturnEmpty() {
            //given
            LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-10.00.00", dateTimeFormatter);
            long brandId = 1;
            long productId = 1111;

            //when
            Rate rateResponse = repository.findByStartDateAndEndDateAndBrandIdAndProductId(brandId, productId, applicationDate);

            //then
            assertNull(rateResponse);

        }


    }


}
