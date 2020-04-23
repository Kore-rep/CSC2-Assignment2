#
# A makefile for CSC2 Assignment 2
# Angus Longmore
# 2020

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= \
	BinaryTreeNode.class \
	BTQueueNode.class \
	BTQueue.class \
	BinaryTree.class \
	LSDataPiece.class \
	AVLTree.class \
	AVLApp.class \
	BinarySearchTree.class \
	BSTApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
cleandocs:
	rm -r $(DOCDIR)/*
run:
	java -cp $(BINDIR) AVLApp
run2:
	java -cp $(BINDIR) AVLApp "8" "16" "20"
run3:
	java -cp $(BINDIR) BSTApp
run4:
	java -cp $(BINDIR) BSTApp "8" "16" "20"
docs:
	javadoc -d $(DOCDIR) src/*.java
