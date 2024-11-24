package io.jcz.init;

import io.jcz.propterties.DataSourceProperty;
import io.jcz.util.EncryptUtil;
import org.springframework.util.StringUtils;

public class DecryptDataSourceInit implements DataSourceInit {
    @Override
    public void beforeCreate(DataSourceProperty dataSourceProperty) {
        String privateKey = dataSourceProperty.getPrivateKey();
        if (StringUtils.hasText(privateKey)) {
            String username = decrypt(privateKey, dataSourceProperty.getUsername());
            dataSourceProperty.setUsername(username);
            String password = decrypt(privateKey, dataSourceProperty.getPassword());
            dataSourceProperty.setPassword(password);
        }
    }

    @Override
    public void afterCreate(DataSourceProperty dataSourceProperty) {

    }

    private String decrypt(String privateKey, String cipherText) {
        if (StringUtils.hasText(cipherText)) {
            return EncryptUtil.rsaDecrypt(cipherText, privateKey, null);
        }
        return cipherText;
    }
}
