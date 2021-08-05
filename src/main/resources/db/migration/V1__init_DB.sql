-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- CATEGORY Table Create SQL
CREATE TABLE CATEGORY
(
    `ID`    BIGINT          NOT NULL    AUTO_INCREMENT,
    `NAME`  VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_FOOD_GROUP PRIMARY KEY (ID)
);


-- FOOD Table Create SQL
CREATE TABLE FOOD
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
CREATE TABLE INSTA_INFO
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
CREATE TABLE INSTA_HASH
(
    `ID`             BIGINT          NOT NULL    AUTO_INCREMENT,
    `HAST_TAG_INFO`  VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_INSTA_HASH PRIMARY KEY (ID)
);


-- USER Table Create SQL
CREATE TABLE USER
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
CREATE TABLE RECOMMENED_FOOD
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
CREATE TABLE INSTA_HASH_INFO
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
CREATE TABLE FOOD_INGREDIENT
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


