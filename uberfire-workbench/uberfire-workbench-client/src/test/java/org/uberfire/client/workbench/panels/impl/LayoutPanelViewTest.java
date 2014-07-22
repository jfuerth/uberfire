package org.uberfire.client.workbench.panels.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.client.workbench.panels.WorkbenchPanelView;
import org.uberfire.client.workbench.panels.support.PartManager;
import org.uberfire.client.workbench.part.WorkbenchPartPresenter;
import org.uberfire.workbench.model.PanelDefinition;
import org.uberfire.workbench.model.PartDefinition;
import org.uberfire.workbench.model.Position;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;

@RunWith(GwtMockitoTestRunner.class)
public class LayoutPanelViewTest {

    @Mock LayoutPanel layoutPanel;
    @Mock PartManager partManager;
    @Mock WorkbenchPartPresenter.View partView;
    @Mock IsWidget wrappedWidget;
    @Mock WorkbenchPartPresenter partPresenter;
    @Mock PartDefinition partDefinition;

    LayoutPanelView view;

    // Not a @Mock or @GwtMock because we want to test the view.init() method
    final LayoutPanelPresenter presenter = mock( LayoutPanelPresenter.class );

    @Before
    public void setup() {
        view = new LayoutPanelView();
        view.layout = layoutPanel;
        view.partManager = partManager;
        view.init(presenter);

        when(partView.getPresenter()).thenReturn(partPresenter);
        when(partView.getWrappedWidget()).thenReturn(wrappedWidget);
        when(partPresenter.getDefinition()).thenReturn(partDefinition);
    }

    @Test
    public void addPresenterOnInit() {
        assertEquals( presenter, view.getPresenter() );
    }

    @Test
    public void addPanel() {
        PanelDefinition panelDefinition = new PanelDefinitionImpl();
        WorkbenchPanelView panelView = mock(WorkbenchPanelView.class);
        Position position = mock(Position.class);

        view.addPanel(panelDefinition, panelView, position);
        verify(layoutPanel).clear();
        verify(layoutPanel).add(panelView);
    }

    @Test
    public void removePanel() {
        view.removePanel();
        verify(layoutPanel, never()).remove(any(Widget.class));
        verify(layoutPanel, never()).remove(any(IsWidget.class));
        verify(layoutPanel, never()).remove(anyInt());
    }

    @Test
    public void clear() {
        view.clear();
        verify(layoutPanel).clear();
    }

    @Test
    public void addNoneExistingPart() {
        when(partManager.hasPart(partDefinition)).thenReturn(false);

        view.addPart(partView);
        verify(partManager).registerPart(partDefinition, eq(any(Widget.class)));
    }

    @Test
    public void addExistingPart() {
        when(partManager.hasPart(partDefinition)).thenReturn(true);

        view.addPart(partView);
        verify(partManager, never()).registerPart(partDefinition, eq(any(Widget.class)));
    }

    @Test
    public void removePart() {
        view.removePart(partDefinition);
        verify(partManager).removePart(partDefinition);
        verify(layoutPanel).clear();
    }

    @Test
    public void selectPart() {
        Widget anyWidget = any(Widget.class);
        when(partManager.selectPart(partDefinition)).thenReturn(anyWidget);

        assertTrue(view.selectPart(partDefinition));
        verify(layoutPanel).clear();
        verify(layoutPanel).add(anyWidget);
    }
}
