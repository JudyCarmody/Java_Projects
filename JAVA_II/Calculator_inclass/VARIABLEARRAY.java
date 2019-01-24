/*
   Java array source code example:
   -  read text file into a variable sized array
   -  sort the array
   -  write array content into another text file.

   Put this text into a file tmp2.java.
*/

import java.io.*;

public class tmp2
{
   // static start point (no object yet)
   public static void main(String args[]) throws Throwable
      { new tmp2().main2(args); }

   // dynamic start point (on a single object)
   public int main2(String args[]) throws Throwable
   {
      if (args.length < 2)
         return 9+perr("supply in- and output filename.");

      String sInFile  = args[0];
      String sOutFile = args[1];

      // full flexible array for text storage:
      int iLinesAlloced  = 10;  // initial array size
      int iLinesUsed     =  0;  // number of lines actually used
      String aTextData[] = new String[iLinesAlloced+10];
      // +10 are safety spaces, to avoid crash on off-by-one errors.

      // read input file into the array.
      BufferedReader fin = new BufferedReader(
         new InputStreamReader(
             new FileInputStream(args[0]), "ISO-8859-1"
             // or US-ASCII,UTF-8,UTF-16BE,UTF-16LE,UTF-16
             ));

      while (true)
      {
         String sLine = fin.readLine();
         if (sLine == null)
            break; // end of file

         // space left in the array?
         if (iLinesUsed >= iLinesAlloced)
         {
            // no: expand the array
            int iNewSize = iLinesAlloced * 2 + 10;
            print("[expanding array to "+iNewSize+" lines]\n");
            String aNewData[] = new String[iNewSize+10];
            if (aNewData == null)
              { perr("out of memory\n"); break; }
            System.arraycopy(aTextData,0, aNewData,0, iLinesUsed);
            aTextData = aNewData;
            // old aTextData is freed implicitely.
            iLinesAlloced = iNewSize;
         }

         aTextData[iLinesUsed] = sLine;

         iLinesUsed++;
      }

      fin.close();

      // do some processing on the array contents
      processData(aTextData, iLinesUsed, iLinesAlloced);

      // write the whole array to output file.
      PrintWriter fout = new PrintWriter(
         new OutputStreamWriter(
             new FileOutputStream(args[1]), "ISO-8859-1"
             ));

      for (int iLine=0; iLine<iLinesUsed; iLine++)
      {
         fout.println(aTextData[iLine]);
      }

      fout.close();

      return 0;
   }

   // processing example: sort array text lines alphabetically
   // with the simplest possible sorting algorithm (bubblesort).
   int processData(String aText[], int iLinesUsed, int iLinesAlloced)
   {
      int iOuterIdx, iInnerIdx;

      for (iOuterIdx=0; iOuterIdx<iLinesUsed; iOuterIdx++)
      {
         for (iInnerIdx=iOuterIdx+1; iInnerIdx<iLinesUsed; iInnerIdx++)
         {
            String sLine1 = aText[iOuterIdx];
            String sLine2 = aText[iInnerIdx];

            if (sLine1.compareTo(sLine2) > 0)
            {
               // swap both lines
               aText[iOuterIdx] = sLine2;
               aText[iInnerIdx] = sLine1;
            }
         }
      }

      print("sorted "+iLinesUsed+" lines.\n");

      return 0;
   }

   // alternative processing example: sort using the Arrays class
   int processData2(String aText[], int iLinesUsed, int iLinesAlloced)
   {
      java.util.Arrays.sort(aText, 0, iLinesUsed);
      return 0;
   }

   static int perr(String s)
      { System.err.print("error: "+s); return 0; }

   static int print(String s)
      { System.out.print(s); return 0; }
};
