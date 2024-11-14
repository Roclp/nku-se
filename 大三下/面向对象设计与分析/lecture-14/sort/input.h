class Input  {
  public:
	Input();
	char * read (char* file_name);
	char * buffer( ) const;
	size_t num_lines() const;

  private:
    size_t _num_lines;
	char * _buffer;
};