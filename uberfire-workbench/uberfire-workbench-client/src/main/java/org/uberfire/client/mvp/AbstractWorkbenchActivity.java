/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uberfire.client.mvp;

import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.workbench.model.Position;
import org.uberfire.workbench.model.menu.Menus;
import org.uberfire.workbench.model.toolbar.ToolBar;

/**
 * Base class for Activities
 */
public abstract class AbstractWorkbenchActivity extends AbstractActivity
        implements
        WorkbenchActivity {

    public AbstractWorkbenchActivity( final PlaceManager placeManager ) {
        super( placeManager );
    }

    @Override
    public Position getDefaultPosition() {
        return Position.ROOT;
    }

    @Override
    public boolean onMayClose() {
        return true;
    }

    @Override
    public void onClose() {
        //Do nothing.
    }

    @Override
    public void onShutdown() {
        //Do nothing.
    }

    @Override
    public abstract String getTitle();

    @Override
    public IsWidget getTitleDecoration() {
        return null;
    }

    @Override
    public abstract IsWidget getWidget();

    @Override
    public void onLostFocus() {
        //Do nothing.  
    }

    @Override
    public void onFocus() {
    }

    @Override
    public Menus getMenus() {
        return null;
    }

    @Override
    public ToolBar getToolBar() {
        return null;
    }

    @Override
    public String contextId() {
        return null;
    }

}
