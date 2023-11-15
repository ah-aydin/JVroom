# JVroom 

## Problem

- you like using neovim or vim motions
- you mainly work in a single page (you don't have 2 files open side-by-side in the same project
- you like not having to use the mouse while writing code
- if you use neovim, you like using [Harpoon](https://github.com/ThePrimeagen/harpoon) to jump between the small set of files that you work on
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
- Navigate between files using `Alt+a` (opens first file in the list), `Alt+s` (opens the second file in the list) and so on.
You can remap them or just bind them to vim keybindings
```
" You can replace the '0' in OpenFileAction0 with any number from 0 to 8
map <M-a> :action com.ofya.jvroom.actions.openfile.OpenFileAction0
```
- Edit files menu usage
  - use `j` and `k` to move down and up the list of files
  - use `Shift+J` and `Shift+K` to move the selected file down and up the list
  - double click `d` to remove file from list
  - `enter` to save changes to list
  - `esc` to discard changes to list

## Notes
- Currently, you can have only 9 files maximum in the list.
- Does not play nice if you want 2 files open side by side. It is made to work with only a single file open at a time