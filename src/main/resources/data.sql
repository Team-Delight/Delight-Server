-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- CATEGORY Table Create SQL
CREATE TABLE IF NOT EXISTS CATEGORY
(
    `ID`    BIGINT          NOT NULL    AUTO_INCREMENT,
    `NAME`  VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_FOOD_GROUP PRIMARY KEY (ID)
);


-- FOOD Table Create SQL
CREATE TABLE IF NOT EXISTS  FOOD
(
    `ID`           BIGINT          NOT NULL    AUTO_INCREMENT,
    `NAME`         VARCHAR(255)    NOT NULL,
    `IMG_URL`      BLOB            NOT NULL,
    `INTRODUCE`    VARCHAR(255)    NOT NULL,
    `CATEGORY_ID`  BIGINT          NULL,
    CONSTRAINT PK_FOOD PRIMARY KEY (ID)
);

ALTER TABLE FOOD
    ADD CONSTRAINT FK_FOOD_CATEGORY_ID_CATEGORY_ID FOREIGN KEY (CATEGORY_ID)
        REFERENCES CATEGORY (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- INSTA_INFO Table Create SQL
CREATE TABLE IF NOT EXISTS  INSTA_INFO
(
    `ID`          BIGINT          NOT NULL    AUTO_INCREMENT,
    `FOOD_ID`     BIGINT          NOT NULL,
    `INSTA_INFO`  VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_INSTA_INFO PRIMARY KEY (ID)
);

ALTER TABLE INSTA_INFO
    ADD CONSTRAINT FK_INSTA_INFO_FOOD_ID_FOOD_ID FOREIGN KEY (FOOD_ID)
        REFERENCES FOOD (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- INSTA_HASH Table Create SQL
CREATE TABLE IF NOT EXISTS INSTA_HASH
(
    `ID`             BIGINT          NOT NULL    AUTO_INCREMENT,
    `HAST_TAG_INFO`  VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_INSTA_HASH PRIMARY KEY (ID)
);


-- USER Table Create SQL
CREATE TABLE IF NOT EXISTS  USER
(
    `ID`           BIGINT          NOT NULL    AUTO_INCREMENT,
    `EMAIL`        VARCHAR(255)    NOT NULL,
    `PASSWORD`     VARCHAR(255)    NOT NULL,
    `INTRODUCE`    VARCHAR(255)    NOT NULL,
    `PROFILE_IMG`  VARCHAR(255)    NOT NULL,
    `USER_ROLE`    VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (ID)
);


-- RECOMMENED_FOOD Table Create SQL
CREATE TABLE IF NOT EXISTS  RECOMMENED_FOOD
(
    `ID`          BIGINT            NOT NULL    AUTO_INCREMENT,
    `FOO_ID`      BIGINT            NOT NULL,
    `NAME`        VARCHAR(255)      NOT NULL,
    `COUNT_RATE`  DECIMAL(18, 0)    NULL        COMMENT '누적 카운트',
    CONSTRAINT PK_RECOMMENED_FOOD PRIMARY KEY (ID)
);

ALTER TABLE RECOMMENED_FOOD
    ADD CONSTRAINT FK_RECOMMENED_FOOD_FOO_ID_FOOD_ID FOREIGN KEY (FOO_ID)
        REFERENCES FOOD (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- INSTA_HASH_INFO Table Create SQL
CREATE TABLE IF NOT EXISTS  INSTA_HASH_INFO
(
    `ID`             BIGINT    NOT NULL    AUTO_INCREMENT,
    `INSTA_INFO_ID`  BIGINT    NOT NULL,
    `INSTA_HASH_ID`  BIGINT    NOT NULL,
    CONSTRAINT PK_INSTA_INFO_HASH PRIMARY KEY (ID)
);

ALTER TABLE INSTA_HASH_INFO
    ADD CONSTRAINT FK_INSTA_HASH_INFO_INSTA_INFO_ID_INSTA_INFO_ID FOREIGN KEY (INSTA_INFO_ID)
        REFERENCES INSTA_INFO (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE INSTA_HASH_INFO
    ADD CONSTRAINT FK_INSTA_HASH_INFO_INSTA_HASH_ID_INSTA_HASH_ID FOREIGN KEY (INSTA_HASH_ID)
        REFERENCES INSTA_HASH (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- FOOD_INGREDIENT Table Create SQL
CREATE TABLE IF NOT EXISTS FOOD_INGREDIENT
(
    `ID`             BIGINT          NOT NULL    AUTO_INCREMENT,
    `FOOD_ID`        BIGINT          NOT NULL,
    `INGREDIENT_01`  VARCHAR(255)    NULL,
    `INGREDIENT_02`  VARCHAR(255)    NULL,
    `INGREDIENT_03`  VARCHAR(255)    NULL,
    `INGREDIENT_04`  VARCHAR(255)    NULL,
    `INGREDIENT_05`  VARCHAR(255)    NULL,
    `INGREDIENT_06`  VARCHAR(255)    NULL,
    `INGREDIENT_07`  VARCHAR(255)    NULL,
    CONSTRAINT PK_FOOD_INGREDIENT PRIMARY KEY (ID)
);

ALTER TABLE FOOD_INGREDIENT
    ADD CONSTRAINT FK_FOOD_INGREDIENT_FOOD_ID_FOOD_ID FOREIGN KEY (FOOD_ID)
        REFERENCES FOOD (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;


insert into CATEGORY (ID, NAME) VALUES (1, '한식');
insert into CATEGORY (ID, NAME) VALUES (2, '중식');
insert into CATEGORY (ID, NAME) VALUES (3, '일식');
insert into CATEGORY (ID, NAME) VALUES (4, '양식');
insert into CATEGORY (ID, NAME) VALUES (5, '패스트푸드');
insert into CATEGORY (ID, NAME) VALUES (6, '분식');

insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('닭볶음탕', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('마라탕', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('누드김밥', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('닭발', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('함박스테이크', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('고구마돈까스', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('모듬튀김', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);


insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('순대', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('홍', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('시', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('창', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('모', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('밥', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('게장', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);


insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('가', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('나', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('다', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('라', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('마', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('사', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('아', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);


insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('보쌈', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('스테이크', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('라면', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('신라면', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('농심', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('자', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('카', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);



insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('타', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('하', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('호호호', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('히히', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('고구마', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('짜장면', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('짬뽕', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);



insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('탕수육', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('라조기', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('소고기', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('무국', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('치킨', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('가라야게', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('만두라면', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);




insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('제육', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('루루', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('돼지갈비', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('찜', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('낙지', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('볶음', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('할리', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);




insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('스타', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('커피', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('김', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('멸치', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('비빔면', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('삼천', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
insert into FOOD (NAME, IMG_URL, INTRODUCE, CATEGORY_ID) VALUES ('사천', 'https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fpost-phinf.pstatic.net%2FMjAxODA1MDhfMTM0%2FMDAxNTI1NzQ0MTE4OTI2.JrcAFQrrYhYMizSDCQID2ATcr3WN6nMMeYGEeGyBtFYg.IFwHFK7Mh5oElhQOQ7WfEJdQRD6ixhEgtJjuQS5J0KIg.JPEG%2FIT2qEDg43TejMaE8sXPQeMPoM6JY.jpg%3Ftype%3Dw540_fst%22&twidth=226&theight=226&opts=17', '닭과 감자를 먹기 좋게 도막 내어 냄비에 넣고 매운 양념장과 버무려 바특하게 끓여 낸 닭고기 요리',1);
