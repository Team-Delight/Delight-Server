-- TAG Table Create SQL ---
CREATE TABLE IF NOT EXISTS TAG
(
    `ID`           BIGINT          NOT NULL    AUTO_INCREMENT,
    `NAME`         VARCHAR(255)    NOT NULL,
    `CREATED_AT`   DATETIME        NOT NULL,
    `MODIFIED_AT`  DATETIME        NULL,
     PRIMARY KEY (ID)
);


-- FOOD_TAG Table Create SQL ---
CREATE TABLE IF NOT EXISTS FOOD_TAG
(
    `ID`           BIGINT      NOT NULL    AUTO_INCREMENT,
    `FOOD_ID`      BIGINT      NOT NULL,
    `TAG_ID`       BIGINT      NOT NULL,
    `CREATED_AT`   DATETIME    NOT NULL,
    `MODIFIED_AT`  DATETIME    NULL,
     PRIMARY KEY (ID)
);

ALTER TABLE FOOD_TAG
    ADD CONSTRAINT FK_FOOD_TAG_TAG_ID_TAG_ID FOREIGN KEY (TAG_ID)
        REFERENCES TAG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE FOOD_TAG
    ADD CONSTRAINT FK_FOOD_TAG_FOOD_ID_FOOD_ID FOREIGN KEY (FOOD_ID)
        REFERENCES FOOD (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;