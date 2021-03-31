/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.imsofa.pagedrecordlist.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import rocks.imsofa.pagedrecordlist.Head;
import rocks.imsofa.pagedrecordlist.Page;
import rocks.imsofa.pagedrecordlist.PagedRecordCollectionList;
import rocks.imsofa.pagedrecordlist.RecordEntry;

/**
 *
 * @author lendle
 */
public class DefaultPagedRecordCollectionListImpl implements PagedRecordCollectionList {

    private Page currentOpenedPage = null;
    private Head head = null;
    private List<Page> bufferedPages = new ArrayList<>();//old->new
    private File rootFolder = null;
    private int MAX_BUFFER_SIZE = 3;
    private long PAGE_SIZE=10000;

    public DefaultPagedRecordCollectionListImpl(File rootFolder) throws Exception {
        this.rootFolder = rootFolder;
        File headFile = new File(rootFolder, "head");
        if (headFile.exists()) {
            this.head = Loader.loadHead(rootFolder);
            long lastPageIndex = Loader.getLastPageIndex(rootFolder);
            if (lastPageIndex != -1) {
                currentOpenedPage = Loader.loadPage(rootFolder, lastPageIndex);
            }
        }else{
            //this is a new list
            this.head=new Head();
            this.head.setPageSize(PAGE_SIZE);
        }
    }

    @Override
    public void save() throws Exception {
        this.save(rootFolder);
    }

    @Override
    public synchronized void save(File folder) throws Exception {
        this.rootFolder = folder;
        Saver.saveHead(rootFolder, head);
        if (this.currentOpenedPage != null) {
            Saver.savePage(rootFolder, currentOpenedPage);
        }
    }

    @Override
    public long size() {
        return head.getLength();
    }

    @Override
    public synchronized RecordEntry get(int index) throws Exception {
        try {
            long pageIndex = (index / head.getPageSize());
            int offset = (int) (index % head.getPageSize());
            for (int i = bufferedPages.size() - 1; i >= 0; i--) {
                Page page = bufferedPages.get(i);
                if (page.getPageIndex() == pageIndex) {
                    return page.getRecordEntries().get(offset);
                }
            }
            //not in buffer
            if (currentOpenedPage != null && currentOpenedPage.getPageIndex() == pageIndex) {
                return currentOpenedPage.getRecordEntries().get(offset);
            }
            //have to load from disk
            Page page = Loader.loadPage(rootFolder, pageIndex);
            //put this page into buffer
            if (bufferedPages.size() == MAX_BUFFER_SIZE) {
                Page swappedOut = bufferedPages.remove(0);
                Saver.savePage(rootFolder, swappedOut);
            }
            bufferedPages.add(page);
            return page.getRecordEntries().get(offset);
        } finally {
//            head.setLength(head.getLength() + 1);
        }
    }

    @Override
    public Head getHead() {
        return head;
    }

    @Override
    public File getFolder() {
        return rootFolder;
    }

    @Override
    public synchronized void add(RecordEntry entry) throws Exception {
        Page availablePage = null;
        if (currentOpenedPage != null) {
            if (currentOpenedPage.getRecordEntries().size() < PAGE_SIZE) {
                availablePage = currentOpenedPage;
            } else {
                //save the currentOpenedPage page, create a new page
                availablePage = new Page(currentOpenedPage.getPageIndex() + 1);
                Saver.savePage(rootFolder, currentOpenedPage);
                currentOpenedPage = availablePage;
            }
        } else {
            long lastPageIndex = Loader.getLastPageIndex(rootFolder);
            availablePage = new Page(0);
            currentOpenedPage = availablePage;
        }
        head.setLength(head.getLength()+1);
        availablePage.getRecordEntries().add(entry);
    }

}
