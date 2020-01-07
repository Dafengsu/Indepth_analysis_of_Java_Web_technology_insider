package Demo.IO.Chapter08;

import Demo.IO.chapter06.DumpFilter;

import java.io.*;

public abstract class LEFilter extends DumpFilter {

  protected LittleEndianInputStream lin;

  public LEFilter(LittleEndianInputStream lin) {
    super(lin);
    this.lin = lin;
  }
  
  public int available() throws IOException {
    return (buf.length - index) + lin.available();
  }
}
