`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/03/07 19:21:59
// Design Name: 
// Module Name: adder8
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////

//8位加法器
module adder8(
    input [7:0] ina,
    input [7:0] inb,
    input inc,
    output [7:0] sum,
    output outc
    );
    assign {outc,sum} = ina + inb + inc;
    
endmodule

////8位加法器
//module lab1_add8(
//    input [7:0]inaa,
//    input [7:0]inbb,
//    input incc,
//    output [7:0]out11,
//    output zz
//    );
//assign {zz,out11}=inaa+inbb+incc;
//endmodule

////32位加法器
//module lab1_add32(
//    input [31:0]ina,
//    input [31:0]inb,
//    input inc,
//    output [31:0]out1,
//    output z
//    );
////reg和wire变量，用来存两个16位加法器各自的结果
//reg c;   //存初始的进位
//reg c1_in;    //存15:8位作加法参与计算的进位（上一位的进位值，这里是c1_out的值）
//reg c2_in;    //存23:16位作加法参与计算的进位（上一位的进位值，这里是c2_out的值）
//reg c3_in;    //存32:24位作加法参与计算的进位（上一位的进位值，这里是c3_out的值）
//wire c1_out;     //存7:0位作加法器计算得到的进位
//wire c2_out;     //存15:8位作加法器计算得到的进位
//wire c3_out;     //存23:15位作加法器计算得到的进位
//wire c4_out;     //存31:24位作加法器计算得到的进位
//wire [7:0]o1;    //存7:0位作加法器计算得到的和
//wire [7:0]o2;    //存15:8位作加法器计算得到的和
//wire [7:0]o3;    //存23:15位作加法器计算得到的和
//wire [7:0]o4;    //存31:24位作加法器计算得到的和
////初始化c的值
//always@(*)
//begin
//c= inc;
//end
////7:0位的加法
//lab1_add8 add1(ina[7:0],inb[7:0],c,o1,c1_out);
////将wire的c1_out赋给reg的c1_in
//always@(*)
//begin
//c1_in = c1_out;
//end
////15:8的加法
//lab1_add8 add2(ina[15:8],inb[15:8],c1_in,o2,c2_out);
////将wire的c2_out赋给reg的c2_in
//always@(*)
//begin
//c2_in = c2_out;
//end
////23:16的加法
//lab1_add8 add3(ina[23:16],inb[23:16],c2_in,o3,c3_out);
////将wire的c2_out赋给reg的c2_in
//always@(*)
//begin
//c3_in = c3_out;
//end
////31:24的加法
//lab1_add8 add4(ina[31:24],inb[31:24],c3_in,o4,c4_out);
////计算输出的值
//assign out1={o4,o3,o2,o1};
//assign z=c4_out;
//endmodule
