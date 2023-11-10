# JVroom 

## Problem

- you like using neovim
- you like using [Harpoon](https://github.com/ThePrimeagen/harpoon) to jump between the small set of files that you work on
- you have to program in java at your job, so you have to use intelliJ

## Usage

- Have the `IdeaVim` plugin installed (it is possible to use it without it but why the heck would you?)
- Map the actions to vim keybindings
```
" Opens edit files menu
map <leader>e :action com.ofya.jvroom.actions.EditFilesAction<CR>

" Adds a file to the list
map <leader>a :action com.ofya.jvroom.actions.AddFileAction<CR>
```
- Navigate between files using `Alt+a`, `Alt+s` and so on. You can remap them or just bind them to vim keybindings
- EditFilesAction usage
  - use `j` and `k` to move down and up the list of files
  - use `Shift+J` and `Shift+K` to move the selected file down and up the list
  - double click `d` to remove file from list
  - enter to save changes to list