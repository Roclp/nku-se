`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/03/07 19:35:29
// Design Name: 
// Module Name: testbench
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

module testbench;
reg [31:0] op1,op2;
reg op3;
wire [31:0] sum;
wire outc;

adder32 a(op1,op2,op3,sum,outc);
//adder8 a7_0(op1[7:0],op2[7:0],op3,sum[7:0],op_1);
//adder8 a15_8(op1[15:8],op2[15:8],op_1,sum[15:8],op_2);
//adder8 a23_16(op1[23:16],op2[23:16],op_2,sum[23:16],op_3);
//adder8 a31_24(op1[31:24],op2[31:24],op_3,sum[31:24],op4);

initial
begin
    op1=32'b0;
    op2=32'b0;
    op3=1'b0;
end

always #3 op1 = $random % 33'b1_0000_0000_0000_0000_0000_0000_0000_0000;
always #5 op2 = $random % 33'b1_0000_0000_0000_0000_0000_0000_0000_0000;
always #7 op3 = $random % 2'b1_0;

endmodule
