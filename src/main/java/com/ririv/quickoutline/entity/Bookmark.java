package com.ririv.quickoutline.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.ririv.quickoutline.textProcess.PreProcess.TwoBlank;

//一个顶级目录为一个bookmark
public class Bookmark implements Serializable {

    //仅在格式为标准格式下使用，如”1.2.5“
    /*
     * TODO：有些书会有”Part Ⅱ“的顶级目录，此时将”1.2.5“向从第二级目录开始处理
     *  一种方案是采用 ”Ⅱ.1.2.5“的形式
     *  另一种方案是，是用postprocess
     * */
    private String seq;
    private String title;
    private Integer pageNum; //偏移后的页码，即pdf中的页码，非真实页码，空为无页码
    private final List<Bookmark> children = new ArrayList<>();
    private Bookmark parent;
    private int level; //0为顶级目录，-1为根结点（隐藏的）
    private int index; //行号，非必要，仅用来记录其所在text中的位置信息
//    private boolean isEmpty = false; //是否有效，如是否为空行


//    public void setEmpty(boolean empty) {
//        isEmpty = empty;
//    }

    /*
     * TODO 对于空行的处理，原将Bookmark添加字段isEmpty的方案已启用，这会使得有效的子Bookmark添加到无效的empty bookmark中
     *  现考虑增加字段：此bookmark随后跟的空行数(int)
     * */

    public Bookmark(String title, Integer pageNum, int level) {
        this.title = title;
        this.pageNum = pageNum;
        this.level = level;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //用来创造根结点，非顶级目录
    public static Bookmark CreateRoot() {
        //"root",原字符串应为"Outlines"
        return new Bookmark("root", -1, -1);
    }

    public int getIndex() {
        return index;
    }

//    public boolean isEmpty() {
//        return isEmpty;
//    }


    public void setSeq(String seq) {
        if (seq == null) {
            this.seq = null;
        }

        //匹配seq标准格式，如"12.3.10"
        else if (seq.matches("^(\\d+\\.)+\\d+$")) {
            this.seq = seq;
        } else {
            this.seq = null;
        }
    }


    public String getSeq() {
        return seq;
    }

    public Optional<Integer> getPageNum() {
        return Optional.ofNullable(pageNum);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public Bookmark getParent() {
        if (parent != null) {
            return parent;
        }
        throw new RuntimeException(title + " 所在bookmark无parent");
    }

    public void setParent(Bookmark parent) {
        this.parent = parent;
    }

    public List<Bookmark> getChildren() {
        return children;
    }

    //得到该bookmark所属的列表,非子列表
    public List<Bookmark> getOwnerList() {
        return this.getParent().getChildren();
    }


    @Override
    public String toString() {
        if (getLevel() == -1) return "root\n";
        else {
            StringBuilder text = new StringBuilder();
            String pageNumStr = getPageNum().map(String::valueOf).orElse("");
            buildLine(text, getLevel(), getTitle(), pageNumStr);
            return text.toString();
        }
    }


    //包含子节点
    public String toText() {
        StringBuilder text = new StringBuilder();
        traverse(this,
                e -> {
                    String pageNumStr = e.getPageNum().map(String::valueOf).orElse("");
                    buildLine(text, e.getLevel(),
                            e.getTitle(), pageNumStr);
                });

        return text.toString();
    }


    /*    Note: 递归，此方法写在工具类中也是一样的，但为了更好地封装，写在了实体类
            设为static，防止人为错误地修改代码，以致于无限调用 子bookmark 中的此递归方法
          */
    public static void traverse(Bookmark bookmark, Consumer<Bookmark> operate) {
        if (bookmark.getLevel() != -1) { //非根节点时
            operate.accept(bookmark);
        }
        if (bookmark.getChildren().size() != 0) { //不要使用isEmpty方法
            for (Bookmark child : bookmark.getChildren()) {
                traverse(child, operate);
            }
        }
    }

    public static void buildLine(StringBuilder text, int level, String title, String pageNum) {
        text.append("\t".repeat(level));
        text.append(title);
        text.append(TwoBlank);
        text.append(pageNum);
        text.append("\n");
    }


}
