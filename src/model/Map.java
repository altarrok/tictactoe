package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map implements Iterable<Block> {
    private List<ArrayList<Block>> blockList = new ArrayList<>();

    private int numRows = 3;
    private int numColumns = 3;

    public Map() {
        //Adding Rows and Columns
        for (int i = 0; i < numRows; i++) {
            blockList.add(new ArrayList<Block>());
            for (int k = 0; k < numColumns; k++) {
                blockList.get(i).add(Block.EMPTY);
            }
        }
    }

    //REQUIRES: 0 <= (x and y) < 3
    public Block getBlock(int x, int y) {
        return blockList.get(x).get(y);
    }

    //REQUIRES: 0 <= (x and y) < 3
    public void setBlock(Block block, int x, int y) {
        blockList.get(x).set(y, block);
    }

    public void reset() {
        blockList = new ArrayList<>();
        //Adding Rows and Columns
        for (int i = 0; i < numRows; i++) {
            blockList.add(new ArrayList<Block>());
            for (int k = 0; k < numColumns; k++) {
                blockList.get(i).add(Block.EMPTY);
            }
        }
    }

    public boolean contains(Block inputBlock) {
        for (Block block : this) {
            if (block == inputBlock) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: 0 <= x < 3
    public List<Block> getRow(int x) {
        return blockList.get(x);
    }

    //REQUIRES: 0 <= x < 3
    public List<Block> getColumn(int y) {
        List<Block> tempList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tempList.add(this.getBlock(i, y));
        }
        return tempList;
    }

    public List<Block> getDiagonal(int i) {
        List<Block> tempList = new ArrayList<>();
        if (i == 0) {
            for (int k = 0; k < 3; k++) {
                tempList.add(this.getBlock(k, k));
            }
        } else {
            for (int k = 0; k < 3; k++) {
                tempList.add(this.getBlock(k, 2 - k));
            }
        }
        return tempList;
    }

    @Override
    public String toString() {
        String str = "";
        int cursor = 0;
        for (Block b : this) {
            if (cursor == 3) {
                cursor = 0;
                str += "\n";
            }
            str += (b == Block.EMPTY ? " " : b.toString()) + "\t";
            cursor++;
        }
        return str;
    }

    @Override
    public Iterator<Block> iterator() {
        return new MapIterator();
    }

    private class MapIterator implements Iterator<Block> {
        int rowCursor = 0;
        int columnCursor = -1;

        @Override
        public boolean hasNext() {
            return (rowCursor < (numRows - 1)) || (columnCursor < numColumns - 1);
        }

        @Override
        public Block next() {
            if (columnCursor < (numColumns - 1)) {
                columnCursor++;
            } else {
                columnCursor = 0;
                rowCursor += 1;
            }
            return getBlock(rowCursor, columnCursor);
        }
    }
}
