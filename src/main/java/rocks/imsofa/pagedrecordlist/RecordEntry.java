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
public class RecordEntry implements Serializable{
    private long globalIndex=-1;
    private Map<String, Serializable> values=new HashMap<>();
}
