#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QList>
#include <QDebug>
#include "category/category.h"
#include "add/categorycreator.h"
#include "add/positioncreator.h"
#include "positions/positions.h"
#include "requester.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);

    ~MainWindow();

    QList<Category*> categoryList;
    QList<positions*> positionList;
    int categoryCount;
    Category* _category;
    positions* _position;

    QString currentCategory;


private slots:
    void on_addCategoryButton_clicked();
    void on_addToppingButton_clicked();
    void on_sendMenuToServerButton_clicked();
    void creatCategory(QString nameCategory);
    void showAddCategoryWidget();
    void deleteCategoryEvent();

    void showAddPositionWidget();
    void creatPosition(QString name,QString cost);
    void categoryClicked();


public slots:
     void repaintPositionList();

private:
    Ui::MainWindow *ui;


};

#endif // MAINWINDOW_H
