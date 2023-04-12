package org.example.url;

import java.util.Stack;

/*
    Given a string path, which is an absolute path (starting with a slash '/')
    to a file or directory in a Unix-style file system, convert it to the simplified canonical path.

    In a Unix-style file system, a period '.' refers to the current directory,
    a double period '..' refers to the directory up a level, and any multiple consecutive
    slashes (i.e. '//') are treated as a single slash '/'. For this problem,
    any other format of periods such as '...' are treated as file/directory names.

    The canonical path should have the following format:
        The path starts with a single slash '/'.
        Any two directories are separated by a single slash '/'.
        The path does not end with a trailing '/'.
        The path only contains the directories on the path from the root directory
            to the target file or directory (i.e., no period '.' or double period '..')
    Return the simplified canonical path.
 */
public class Path {

    public String simplifyPath(final String path) {
        final Stack<String> stack = new Stack<>();
        final String[] skip = {"..", ".", ""};
        for (final String s : path.split("/")) {
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!(s.equals("") || s.equals(".") || s.equals(".."))) {
                stack.add(s);
            }
        }
        String result = "";
        for (final String elm : stack) {
            result += "/" + elm;
        }
        return result.isEmpty() ? "/" : result;
    }

}
