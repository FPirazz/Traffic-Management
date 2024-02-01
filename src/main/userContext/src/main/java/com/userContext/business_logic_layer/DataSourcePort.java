package com.userContext.business_logic_layer;

import com.userContext.business_logic_layer.exceptions.DataSourceException;

public interface DataSourcePort {

    void saveUser() throws DataSourceException;

}
