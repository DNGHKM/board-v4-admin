package com.boardv4admin.enums;

public enum BoardType {
    NOTICE("noticeList", "noticeWrite", "noticeModify"),
    FREE("freeList", "freeWrite", "freeModify"),
    GALLERY("galleryList", "galleryWrite", "galleryModify");

    private final String listView;
    private final String writeView;
    private final String modifyView;

    BoardType(String listView, String writeView, String modifyView) {
        this.listView = listView;
        this.writeView = writeView;
        this.modifyView = modifyView;
    }

    public String getListView() {
        return "list/" + listView;
    }

    public String getWriteView() {
        return "write/" + writeView;
    }

    public String getModifyView() {
        return "modify/" + modifyView;
    }
}
