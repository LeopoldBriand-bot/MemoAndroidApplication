package memo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class MemoDAO {
    @Query("SELECT * FROM memos")
    public  abstract List<Memo> getListMemos();

    @Query("SELECT COUNT(*) FROM memos WHERE text = :text")
    public abstract long countMemosByText(String text);

    @Insert
    public abstract void insert(Memo... courses);

    @Update
    public abstract void update(Memo... courses);

    @Delete
    public abstract void delete(Memo... courses);
}