`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/10/25 14:36:10
// Design Name: 
// Module Name: tb
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

//module tb，信号类型声明，input定义为reg型，output定义为wire型
module tb;
//input
    reg  clk;
    reg  wen;
    reg [4:0] raddr1;
    reg [4:0] raddr2;
    reg [4:0] waddr;
    reg [31:0] wdata;
    reg [4:0] test_addr;
//output
    wire [31:0] rdata1;
    wire [31:0] rdata2;
    wire [31:0] test_data;
//实例化regfile模块，模块实例化写法    
//Instantiate the unit under test
    regfile rf(
      .clk(clk),
      .wen(wen),
      .raddr1(raddr1),
      .raddr2(raddr2),
      .waddr(waddr),
      .test_addr(test_addr),
      .rdata1(rdata1),
      .rdata2(rdata2),
      .test_data(test_data)
      );
      
  initial begin
//initialize Inputs
     clk = 0;
     wen = 0;
     raddr1 = 0;
     raddr2 = 0;
     waddr = 0;
     wdata = 0;
     test_addr = 0;
//wait 100ns for global reset to finish
     #100;
//write
     #10;
     waddr = 5'H3;
     wdata = 32'H7d;
     #40;
     wen = 1'b1;
     #50;
     wen = 1'b0;

//read
     #100;
     raddr1 = 5'H3;
     #100;
     raddr2 = 5'H3;
     #100;
     test_addr = 5'H3;





end
     always #5 clk = ~clk;
endmodule
