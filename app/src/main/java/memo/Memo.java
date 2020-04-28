package memo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity(tableName = "memos")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    public Long memoId;
    public String text;
    public Boolean status;
    public String description;
    public Memo() { }
    public Memo(String text, Boolean status, String description) {
        this.text = text;
        this.status = status;
        this.description = description;
    }

    public void changeStatus() {
        this.status = !this.status;
    }
}
