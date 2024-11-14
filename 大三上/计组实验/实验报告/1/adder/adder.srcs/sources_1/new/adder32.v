`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/03/14 22:35:01
// Design Name: 
// Module Name: adder32
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


module adder32(
    input [31:0] op1,
    input [31:0] op2,
    input op3,
    output [31:0] sum,
    output outc
    );
//    reg [31:0] op1,op2;
//    reg op3;
//    wire [31:0] sum;
    wire op_1,op_2,op_3;

    adder8 a7_0(op1[7:0],op2[7:0],op3,sum[7:0],op_1);
    adder8 a15_8(op1[15:8],op2[15:8],op_1,sum[15:8],op_2);
    adder8 a23_16(op1[23:16],op2[23:16],op_2,sum[23:16],op_3);
    adder8 a31_24(op1[31:24],op2[31:24],op_3,sum[31:24],outc);
    
    
    
endmodule
