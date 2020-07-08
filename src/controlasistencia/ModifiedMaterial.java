/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import javax.swing.BorderFactory;
import javax.swing.UIDefaults;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.themes.AbstractMaterialTheme;
import mdlaf.utils.MaterialBorders;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import mdlaf.utils.MaterialImageFactory;

/**
 *
 * @author BB_TLACUACHE
 */
public class ModifiedMaterial extends AbstractMaterialTheme {

    
    @Override
    public void installTheme() {
        installColor();
        installFonts();
        installBorders();
        installIcons();
    }

    @Override
    protected void installFonts(){
        this.fontBold = MaterialFontFactory.getInstance().getFont(MaterialFontFactory.BOLD);
        this.fontItalic = MaterialFontFactory.getInstance().getFont(MaterialFontFactory.ITALIC);
        this.fontMedium = MaterialFontFactory.getInstance().getFont(MaterialFontFactory.MEDIUM);
        this.fontRegular = MaterialFontFactory.getInstance().getFont(MaterialFontFactory.REGULAR);

        super.borderTitledBorder = Fonts.GREEN_LINE_BORDER;
    }

    @Override
    protected void installIcons() {
        this.selectedCheckBoxIcon = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX, highlightBackgroundPrimary);
        this.unselectedCheckBoxIcon = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX_OUTLINE_BLANK);

        this.selectedRadioButtonIcon = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.RADIO_BUTTON_BLACK_ON);
        this.unselectedRadioButtonIcon = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.RADIO_BUTTON_BLACK_OFF);

        this.selectedCheckBoxIconTable = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX, highlightBackgroundPrimary);
        this.unselectedCheckBoxIconTable = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX_OUTLINE_BLANK);
        this.selectedCheckBoxIconSelectionRowTable = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX, highlightBackgroundPrimary);
        this.unselectedCheckBoxIconSelectionRowTable = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CHECK_BOX_OUTLINE_BLANK);

        this.closedIconTree = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.KEYBOARD_ARROW_RIGHT);
        this.openIconTree = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.KEYBOARD_ARROW_DOWN, highlightBackgroundPrimary);

        //this.yesCollapsedTaskPane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.YES_COLLAPSED);
        this.noCollapsedTaskPane = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.KEYBOARD_ARROW_RIGHT);

        //this.noCollapsedTaskPane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.NO_COLLAPSED);
        this.yesCollapsedTaskPane = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.KEYBOARD_ARROW_DOWN);

        this.warningIconOptionPane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.WARNING);
        this.errorIconIconOptionPane =  MaterialImageFactory.getInstance().getImage(MaterialImageFactory.ERROR);
        this.questionIconOptionPane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.QUESTION);
        this.informationIconOptionPane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.INFORMATION);

        this.iconComputerFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.COMPUTER_BLACK);
        this.iconDirectoryFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.FOLDER_BLACK);
        this.iconFileFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.FILE_BLACK);
        this.iconFloppyDriveFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.FLOPPY_DRIVE_BLACK);
        this.iconHardDriveFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.HARD_DRIVE_BLACK);
        this.iconHomeFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.HOME_BLACK);
        this.iconListFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.LIST_BLACK);
        this.iconDetailsFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.DETAILS_BLACK);
        this.iconNewFolderFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.NEW_FOLDER_BLACK);
        this.iconNewFolderFileChooser = MaterialImageFactory.getInstance().getImage(GoogleMaterialDesignIcons.CREATE_NEW_FOLDER);
        this.iconUpFolderFileChooser = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.BACK_ARROW_BLACK);

        this.unselectedIconToggleButton = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.TOGGLE_BUTTON_BLACK_OFF);
        this.selectedIconToggleButton = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.TOGGLE_BUTTON_BLACK_ON);

        super.iconCloseTitlePane = MaterialImageFactory.getInstance().getImage(MaterialImageFactory.CLOSE_WINDOWS_BLACK);
    }

    @Override
    protected void installBorders() {
        super.installBorders();

        this.borderComboBox = MaterialBorders.roundedLineColorBorder(MaterialColors.GRAY_900, 12);
        
        this.borderTable = MaterialBorders.LIGHT_LINE_BORDER;
        this.borderTableHeader = MaterialBorders.LIGHT_SHADOW_BORDER;
//        this.setTabInsetsTabbedPane(new InsetsUIResource(2, 2, 2, 2));
//        this.selectedTabInsetsTabbedPane = new InsetsUIResource(2, 2, 2, 2);
    }

    @Override
    protected void installColor() {
        this.backgroundPrimary = MaterialColors.WHITE;
        this.highlightBackgroundPrimary = new ColorUIResource(209,87,129);

        this.textColor = new ColorUIResource(52,46,55);
        this.disableTextColor = MaterialColors.GRAY_800;

        this.buttonBackgroundColor = MaterialColors.COSMO_LIGTH_GRAY;
        this.buttonBackgroundColorMouseHover = Fonts.PINK;
        this.buttonTextColor = new ColorUIResource(52,46,55);
        this.buttonDefaultBackgroundColorMouseHover = Fonts.PURPLE;
        this.buttonDefaultBackgroundColor = Fonts.DARK;
        this.buttonDefaultTextColor = MaterialColors.WHITE;
        this.buttonDisabledBackground = MaterialColors.COSMO_DARK_GRAY;
        this.buttonDisabledForeground = MaterialColors.GRAY_500;
        this.buttonFocusColor = MaterialColors.GRAY_900;
        this.buttonDefaultFocusColor = Fonts.PURPLE;
        this.buttonBorderColor = MaterialColors.COSMO_MEDIUM_GRAY;
        this.buttonColorHighlight = MaterialColors.GRAY_400;

        this.selectedInDropDownBackgroundComboBox = Fonts.PINK;
        this.selectedForegroundComboBox = new ColorUIResource(234,239,211);

        this.menuBackground = MaterialColors.WHITE;
        this.menuBackgroundMouseHover = MaterialColors.GRAY_400;
        this.menuTextColor = new ColorUIResource(52,46,55);
        this.menuDisableBackground = MaterialColors.TRANSPANENT;

        this.arrowButtonColorScrollBar = MaterialColors.GRAY_200;
        this.trackColorScrollBar = MaterialColors.GRAY_200;
        this.thumbColorScrollBar = MaterialColors.GRAY_500;
        this.thumbDarkShadowColorScrollBar = MaterialColors.GRAY_500;
        this.thumbHighlightColorScrollBar = MaterialColors.GRAY_500;
        this.thumbShadowColorScrollBar = MaterialColors.GRAY_500;
        this.arrowButtonOnClickColorScrollBar = MaterialColors.GRAY_400;
        this.mouseHoverColorScrollBar = MaterialColors.GRAY_300;

        this.trackColorSlider = new ColorUIResource(52,46,55);
        this.haloColorSlider = MaterialColors.bleach(new ColorUIResource(209,87,129), 0.5f);

        this.highlightColorTabbedPane = MaterialColors.GRAY_200;
        this.borderHighlightColorTabbedPane = MaterialColors.GRAY_300;
        this.focusColorLineTabbedPane = Fonts.PINK;
        this.disableColorTabTabbedPane = MaterialColors.COSMO_STRONG_GRAY;

        this.backgroundTable = MaterialColors.WHITE;
        this.backgroundTableHeader = MaterialColors.GRAY_200;
        this.foregroundTable = MaterialColors.BLACK;
        this.foregroundTableHeader = MaterialColors.BLACK;
        this.selectionBackgroundTable = MaterialColors.COSMO_LIGTH_BLUE;
        this.selectionForegroundTable = MaterialColors.BLACK;
        this.gridColorTable = MaterialColors.GRAY_200;
        this.alternateRowBackgroundTable = MaterialColors.GRAY_300;

        this.dockingBackgroundToolBar = MaterialColors.LIGHT_GREEN_A100;
        this.floatingBackgroundToolBar = MaterialColors.GRAY_200;

        this.selectionBackgroundTree = super.backgroundPrimary;
        this.selectionBorderColorTree = super.backgroundPrimary;

        this.backgroundTextField = MaterialColors.GRAY_200;
        this.inactiveForegroundTextField = MaterialColors.GRAY_800;
        this.inactiveBackgroundTextField = MaterialColors.GRAY_200;
        this.selectionBackgroundTextField = new ColorUIResource(209,87,129);
        this.selectionForegroundTextField = new ColorUIResource(234,239,211);
        super.disabledBackgroudnTextField = MaterialColors.GRAY_300;
        super.disabledForegroundTextField = MaterialColors.GRAY_700;
        this.inactiveColorLineTextField = new ColorUIResource(52,46,55);
        this.activeColorLineTextField = new ColorUIResource(209,87,129);

        this.arrowButtonBackgroundSpinner = MaterialColors.GRAY_200;
        this.mouseHoverButtonColorSpinner = Fonts.PINK;

        this.titleBackgroundGradientStartTaskPane = MaterialColors.GRAY_300;
        this.titleBackgroundGradientEndTaskPane = MaterialColors.GRAY_500;
        this.titleOverTaskPane = MaterialColors.LIGHT_BLUE_500;
        this.specialTitleOverTaskPane = MaterialColors.LIGHT_BLUE_500;
        this.backgroundTaskPane = MaterialColors.WHITE;
        this.borderColorTaskPane = MaterialColors.WHITE;
        this.contentBackgroundTaskPane = MaterialColors.WHITE;

        this.selectionBackgroundList = MaterialColors.COSMO_LIGTH_BLUE;
        this.selectionForegroundList = MaterialColors.BLACK;

        this.backgroundProgressBar = MaterialColors.COSMO_LIGTH_BLUE;
        this.foregroundProgressBar = MaterialColors.COSMO_BLUE;

        this.withoutIconSelectedBackgroundToggleButton = MaterialColors.COSMO_DARK_GRAY;
        this.withoutIconSelectedForegoundToggleButton = MaterialColors.BLACK;
        this.withoutIconBackgroundToggleButton = MaterialColors.GRAY_300;
        this.withoutIconForegroundToggleButton = MaterialColors.BLACK;

        this.colorDividierSplitPane = MaterialColors.COSMO_DARK_GRAY;
        this.colorDividierFocusSplitPane = MaterialColors.COSMO_BLUE;

        super.colorTextTitledBorder = textColor;

        super.backgroundSeparator = MaterialColors.GRAY_300;
        super.foregroundSeparator = MaterialColors.GRAY_300;
    }

    @Override
    public void installUIDefault(UIDefaults table) {
        super.installUIDefault(table);
    }

    @Override
    public String getName() {
        return "Material Lite";
    }
    
}
