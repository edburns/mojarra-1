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

package com.sun.faces.config.rules;


import org.xml.sax.Attributes;

import com.sun.faces.config.beans.DescriptionBean;
import com.sun.faces.config.beans.FeatureBean;


/**
 * <p>Digester rule for the <code>&lt;description&gt;</code> element.</p>
 */

public class DescriptionRule extends FeatureRule {


    private static final String CLASS_NAME =
        "com.sun.faces.config.beans.DescriptionBean";


    // ------------------------------------------------------------ Rule Methods


    /**
     * <p>Create or retrieve an instance of <code>DescriptionBean</code>
     * and push it on to the object statck.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     * @param attributes The attribute list of this element
     *
     * @exception IllegalStateException if the parent stack element is not
     *  of type FeatureBean
     */
    public void begin(String namespace, String name,
                      Attributes attributes) throws Exception {

        FeatureBean fb = null;
        try {
            fb = (FeatureBean) digester.peek();
        } catch (Exception e) {
            throw new IllegalStateException
                ("No parent FeatureBean on object stack");
        }
        String lang = attributes.getValue("lang");
        if (lang == null) {
            lang = attributes.getValue("xml:lang"); // If digester not ns-aware
        }
        if (lang == null) {
            lang = ""; // Avoid NPE errors on sorted map comparisons
        }
        DescriptionBean db = fb.getDescription(lang);
        if (db == null) {
            if (digester.getLogger().isDebugEnabled()) {
                digester.getLogger().debug("[DescriptionRule]{" +
                                           digester.getMatch() +
                                           "} New (" + lang + ")");
            }
            Class clazz =
                digester.getClassLoader().loadClass(CLASS_NAME);
            db = (DescriptionBean) clazz.newInstance();
            db.setLang(lang);
            fb.addDescription(db);
        } else {
            if (digester.getLogger().isDebugEnabled()) {
                digester.getLogger().debug("[DescriptionRule]{" +
                                           digester.getMatch() +
                                           "} Old " + lang + ")");
            }
        }
        digester.push(db);

    }


    /**
     * <p>No body processing is requlred.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     * @param text The text of the body of this element
     */
    public void body(String namespace, String name,
                     String text) throws Exception {
    }


    /**
     * <p>Pop the <code>DescriptionBean</code> off the top of the stack.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     *
     * @exception IllegalStateException if the popped object is not
     *  of the correct type
     */
    public void end(String namespace, String name) throws Exception {

        DescriptionBean top = null;
        try {
            top = (DescriptionBean) digester.pop();
        } catch (Exception e) {
            throw new IllegalStateException("Popped object is not a " +
                                            CLASS_NAME + " instance");
        }
        if (digester.getLogger().isDebugEnabled()) {
            digester.getLogger().debug("[DescriptionRule]{" +
                                       digester.getMatch() +
                                       "} Pop (" + top.getLang() + ")");
        }


    }


    /**
     * <p>No finish processing is required.</p>
     *
     */
    public void finish() throws Exception {
    }


    // ---------------------------------------------------------- Public Methods


    public String toString() {

        StringBuffer sb = new StringBuffer("DescriptionRule[className=");
        sb.append(CLASS_NAME);
        sb.append("]");
        return (sb.toString());

    }


    // --------------------------------------------------------- Package Methods


}
