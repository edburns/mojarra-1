/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.faces.config.beans;


/**
 * <p>Configuration bean for <code>&lt;list-entry&gt; element.</p>
 */

public class ListEntryBean implements NullValueHolder {


    // -------------------------------------------------------------- Properties


    private String value;
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }


    // -------------------------------------------------------------- Extensions


    // ------------------------------------------------- NullValueHolder Methods

    private boolean nullValue = false;
    public boolean isNullValue() { return nullValue; }
    public void setNullValue(boolean nullValue) { this.nullValue = nullValue; }


    // ----------------------------------------------------------------- Methods


}
