package com.model.br;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.util.br.DataTableData;

@ManagedBean(name= ListSelectionBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class ListSelectionBean implements Serializable {
    public static final String BEAN_NAME = "listSelectionBean";
    public String getBeanName() { return BEAN_NAME; }
    
    private List<SelectItem> stringList = new ArrayList<SelectItem>() {{
        for (String s : DataTableData.CHASSIS_ALL) {
            add(new SelectItem(s));
        }
    }};
    private Set<Object> selections;
    private boolean multiSelect = true;

    public List<SelectItem> getStringList() {
        return stringList;
    }

    public void setStringList(List<SelectItem> stringList) {
        this.stringList = stringList;
    }
    
    public Set<Object> getSelections() {
        return selections;
    }
    
    public List<Object> getSelectionList() {
        if (selections != null) {
            return new ArrayList<Object>(selections);
        }
        return null;
    }
    
    public void setSelections(Set<Object> selections) {
        this.selections = selections;
    }
    
    public boolean getMultiSelect() {
        return multiSelect;
    }
    
    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }
}