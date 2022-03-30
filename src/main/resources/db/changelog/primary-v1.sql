--liquibase formatted sql
--changeset abhishek:1 splitStatements:true endDelimiter:; labels:"pppp" runOnChange:true
ALTER TABLE test_liquibase ADD "primary" varchar(50);

--rollback alter table test_liquibase drop column "primary";