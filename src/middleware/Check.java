package middleware;

import model.Block;
import model.Map;

import java.util.HashSet;
import java.util.Set;

public class Check {
    public static boolean playable(Map map, int x, int y) {
        return map.getBlock(x,y) == Block.EMPTY;
    }

    public static boolean mapFull(Map map) {
        return !map.contains(Block.EMPTY);
    }

    // Returns Block.EMPTY if not ended
    public static Block winner(Map map) {
        //Rows
        for (int i = 0; i < 3; i++) {
            Set<Block> rowSet = new HashSet<>(map.getRow(i));
            if (rowSet.size() <= 1) {
                if (rowSet.contains(Block.X)) {
                    return Block.X;
                }

                if (rowSet.contains(Block.O)) {
                    return Block.O;
                }
            }
        }

        //Columns
        for (int i = 0; i < 3; i++) {
            Set<Block> columnSet = new HashSet<>(map.getColumn(i));
            if (columnSet.size() <= 1) {
                if (columnSet.contains(Block.X)) {
                    return Block.X;
                }

                if (columnSet.contains(Block.O)) {
                    return Block.O;
                }
            }
        }

        //Diagonals
        for (int i = 0; i < 2; i++) {
            Set<Block> diagonalSet = new HashSet<>(map.getDiagonal(i));
            if (diagonalSet.size() <= 1) {
                if (diagonalSet.contains(Block.X)) {
                    return Block.X;
                }

                if (diagonalSet.contains(Block.O)) {
                    return Block.O;
                }
            }
        }


        //Checking if map is full
        if (mapFull(map)) {
            return Block.DRAW;
        }

        return Block.EMPTY;
    }
}
