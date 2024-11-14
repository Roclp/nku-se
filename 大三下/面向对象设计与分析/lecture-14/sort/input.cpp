#include "stdafx.h"
#include "input.h"
#include "stdio.h"

Input::Input()
{
	_num_lines = 0;
	_buffer    = NULL;
}

char * Input::read (char* file_name)
{
	// read the entire content of the file into a buffer
	FILE * f;
	f=fopen(file_name, "rb");
    fseek(f, 0, SEEK_END );
	size_t length = ftell(f);
	// \0 should be appended after the last char
	_buffer = new char [length + 1]; 
	fseek(f, 0, SEEK_SET );
	fread(_buffer, 1, length, f );
	fclose(f);
	
	// replace new_line characters with \0	
	for (size_t i = 0; i<length; i++) {
		if (_buffer[i] == '\r' )  
			_buffer[i] = '\0'; 			
		
		if (_buffer[i] == '\n')  {
			_buffer[i] = '\0'; 
			_num_lines ++;
		}
	}
	_buffer[length] = '\0';
    _num_lines ++;   // there is no newline at the file end. 
	return _buffer;
}

char * Input::buffer( ) const
{
	return _buffer;
}

size_t Input::num_lines() const
{
	return _num_lines;
}