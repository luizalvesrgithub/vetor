package com.util.br;

/*
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is ICEfaces 1.5 open source software code, released
 * November 5, 2006. The Initial Developer of the Original Code is ICEsoft
 * Technologies Canada, Corp. Portions created by ICEsoft are Copyright (C)
 * 2004-2011 ICEsoft Technologies Canada, Corp. All Rights Reserved.
 *
 * Contributor(s): _____________________.
 */



import java.io.Serializable;
import com.util.br.Car;

public class SelectableCar extends Car implements Serializable{
    private boolean selected = false;
    
    public SelectableCar() {
        super();
    }
    
	public SelectableCar(int id,
	           String name, String chassis,
	           int weight, int acceleration, 
	           double mpg, double cost) {
	    super(id, name, chassis, weight, acceleration, mpg, cost);
    }
    
    public SelectableCar(Car base) {
        super(base.getId(),
              base.getName(),
              base.getChassis(),
              base.getWeight(),
              base.getAcceleration(),
              base.getMpg(),
              base.getCost());
    }
    
    public boolean isSelected() { return selected; }
    
    public void setSelected(boolean selected) { this.selected = selected; }
}
