fun pascal(column: Int, row: Int): Int =
    if (row == 0)
        if (column == 0) 1
        else 0
    else pascal(column-1, row-1) + pascal(column, row-1)
