package com.team.delightserver.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class UpperCaseTableConfig extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String tableName = toSnakeCase(name.getText()).toUpperCase();
        return Identifier.toIdentifier(tableName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String columnName = toSnakeCase(name.getText()).toUpperCase();
        return Identifier.toIdentifier(columnName);
    }

    public String toSnakeCase(String from) {
        String regex = "([a-z])([A-Z0-9]+)";
        String replacement = "$1_$2";
        return from.replaceAll(regex, replacement);
    }
}