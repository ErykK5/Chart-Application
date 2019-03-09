package com.conversion;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class ReadFile {

    private float[] Ld;
    private float[][] graphArr;
    private float[] Dd;
    private float[][][][] allArray;

    public ReadFile() {
        this.Ld = new float[120];
        this.graphArr = new float[38][120];
        this.Dd = new float[]{ 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 10.0f, 12.0f, 14.0f, 16.0f, 24.0f, 32.0f };
        this.allArray = new float[15][38][38][120];
    }

    public void read() {
        final String fileName = ".\\src\\BAZA2.bin";
        File file = new File(fileName);
        float[] result = new float[(int) file.length()/4];
        float[] tmpLd = new float[120];
        float[][] tmpGraphArr = new float[38][120];
        float[][][][] tmpAllArray = new float[15][38][38][120];

        try {
            FileChannel fileChannel = null;
            ByteBuffer byteBuffer = null;
            fileChannel = (FileChannel) Files.newByteChannel(Paths.get(fileName), StandardOpenOption.READ);

            for( int i = 0; i < 2841912; i++ ) {
                byteBuffer = ByteBuffer.allocate(4);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                fileChannel.read(byteBuffer);
                byteBuffer.flip();
                result[i] = byteBuffer.getFloat();
            }

            int t = 2;
            int d = 0;
            int d1 = 0;
            int d2 = 0;
            int d3 = 0;
            int x = 177732;
            for( int k = 0, j = 0, m = 0; k < 2841912; k++ ) {

                if( k < 120 ) {
                    tmpLd[k] = result[k];
                }

                if( k > 122 && j < 38 ) {
                    tmpGraphArr[j][m++] = result[k];
                    if( m == 120 ) {
                        m = 0;
                        k += 3;
                        j++;
                    }
                }

                if(((x * t) - (120 * (t - 1)) + 3) == k) {
                    t++;
                    d++;
                    d1 = 0;
                }

                if( k > x+2 ) {
                    tmpAllArray[d][d1][d2][d3++] = result[k];
                    if( d3 == 120 ) {
                        d3 = 0;
                        d2++;
                        k += 3;
                    }
                    if( d2 == 38 ) {
                        d2 = 0;
                        d1++;
                    }
                    if( d1 == 38 ) {
                        d1 = 0;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        setLd(tmpLd);
        setGraphArr(tmpGraphArr);
        setAllArray(tmpAllArray);
    }

    public float[][][][] getAllArray() {
        return allArray;
    }

    public void setAllArray(float[][][][] allArray) {
        this.allArray = allArray;
    }

    public float[] getDd() {
        return Dd;
    }

    public void setDd(float[] dd) {
        Dd = dd;
    }

    public float[][] getGraphArr() {
        return graphArr;
    }

    public void setGraphArr(float[][] graphArr) {
        this.graphArr = graphArr;
    }

    public float[] getLd() {
        return Ld;
    }

    public void setLd(float[] dd) {
        Ld = dd;
    }
}