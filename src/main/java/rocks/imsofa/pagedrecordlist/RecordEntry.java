/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lendle
 */
public class RecordEntry{
    private Map<String, Object> values=new HashMap<>();
    public void setValue(String key, Object value){
        this.values.put(key, value);
    }
    public Object getValue(String key){
        return this.values.get(key);
    }
}
