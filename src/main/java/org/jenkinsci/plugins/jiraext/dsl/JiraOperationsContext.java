/***************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 **************************************************************************/
package org.jenkinsci.plugins.jiraext.dsl;

import hudson.Extension;
import javaposse.jobdsl.dsl.Context;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import org.jenkinsci.plugins.jiraext.view.AddComment;
import org.jenkinsci.plugins.jiraext.view.AddFixVersion;
import org.jenkinsci.plugins.jiraext.view.AddLabel;
import org.jenkinsci.plugins.jiraext.view.AddLabelToField;
import org.jenkinsci.plugins.jiraext.view.JiraOperationExtension;
import org.jenkinsci.plugins.jiraext.view.Transition;
import org.jenkinsci.plugins.jiraext.view.UpdateField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dalvizu
 */
@Extension(optional=true)
public class JiraOperationsContext
    extends ContextExtensionPoint
    implements Context
{
    List<JiraOperationExtension> extensions = new ArrayList<>();

    public void addComment(String comment, boolean postCommentForEveryCommit) {
        extensions.add(new AddComment(postCommentForEveryCommit, comment));
    }

    public void addLabel(String label) {
        extensions.add(new AddLabel(label));
    }

    public void transition(String transitionName) {
        extensions.add(new Transition(transitionName));
    }

    public void updateField(String fieldName, String fieldValue) {
        extensions.add(new UpdateField(fieldName, fieldValue));
    }

    public void addLabelToField(String fieldName, String fieldValue) {
        extensions.add(new AddLabelToField(fieldName, fieldValue));
    }

    public void addFixVersion(String fixVersion) {
        extensions.add(new AddFixVersion(fixVersion));
    }

}
