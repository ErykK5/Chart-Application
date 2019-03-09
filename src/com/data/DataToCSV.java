package com.data;

import com.conversion.ReadFile;

import java.io.*;

public class DataToCSV {

    public void start() {
        ReadFile readFile = new ReadFile();
        readFile.read();
        float[] ld = readFile.getLd();
        float[][] graphArr = readFile.getGraphArr();
        float[][][][] allArr = readFile.getAllArray();
        float[] dD = readFile.getDd();


        for ( int j=0; j<16; j++ ) {
            for (int i = 0; i < 38; i++) {
                File file = new File(".\\resources\\data\\graphs" + (int)( 10* dD[j])  + "\\graph" + (i + 1) + ".csv");
                file.getParentFile().mkdirs();
                try (Writer writer = new BufferedWriter(

                        new OutputStreamWriter(
                                new FileOutputStream(file),"UTF-8"))) {
                    if( j == 0 )
                        writeToCSV(ld, graphArr[i], writer);

                    if( j != 0 ) {
                        for (int k = 0; k < 38; k++) {
                            File file1 = new File(".\\resources\\data\\graphs" + (int)( 10* dD[j]) + "\\rirm" + (i+1) +"\\graph" + (k + 1) + ".csv");
                            file1.getParentFile().mkdirs();
                            try (Writer writer2 = new BufferedWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(file1),"UTF-8"))) {

                                writeToCSV(ld, allArr[j-1][i][k], writer2);  //check if ok

                            } catch (FileNotFoundException ex) {
                                ex.getStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    ex.getStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void writeToCSV( float[] Ld, float[] data, Writer writer ) {
        for( int i = 0; i < 120; i++ ) {
            try {
                writer.write("\"" + Ld[i] + "\",\"" + data[i] + "\"\n");
            }
            catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }
}
